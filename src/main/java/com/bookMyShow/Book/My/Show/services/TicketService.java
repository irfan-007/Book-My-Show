package com.bookMyShow.Book.My.Show.services;

import com.bookMyShow.Book.My.Show.enums.SeatType;
import com.bookMyShow.Book.My.Show.models.Show;
import com.bookMyShow.Book.My.Show.models.ShowSeat;
import com.bookMyShow.Book.My.Show.models.Ticket;
import com.bookMyShow.Book.My.Show.models.User;
import com.bookMyShow.Book.My.Show.repositories.ShowRepository;
import com.bookMyShow.Book.My.Show.repositories.ShowSeatsRepository;
import com.bookMyShow.Book.My.Show.repositories.TicketRepository;
import com.bookMyShow.Book.My.Show.repositories.UserRepository;
import com.bookMyShow.Book.My.Show.requestDTOs.BookTicketRequest;
import com.bookMyShow.Book.My.Show.responseDTOs.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatsRepository showSeatsRepository;
    @Autowired
    private UserRepository userRepository;
    public TicketResponse bookTicket(BookTicketRequest bookTicketRequest){
        // find show and user
        Show show=showRepository.findById(bookTicketRequest.getShowId()).get();
        User user=userRepository.findById(bookTicketRequest.getUserId()).get();
        // book the seats and calculate total amount
        List<ShowSeat> showSeatList=show.getShowSeatList();
        Integer total=0;
        for(ShowSeat showSeat:showSeatList){
            String seatNo=showSeat.getSeatNo();
            for(int i=0;i<bookTicketRequest.getBookingSeats().size();i++){

                String bookingSeatNo=bookTicketRequest.getBookingSeats().get(i);
                Boolean isFoodAttatched=bookTicketRequest.getIsFoodAttached().get(i);

                if(seatNo.equals(bookingSeatNo)){
                    showSeat.setIsBooked(Boolean.TRUE);
                    showSeat.setIsFoodAttached(isFoodAttatched);
                    if(showSeat.getSeatType().equals(SeatType.CLASSIC))
                        total+=100;
                    else
                        total+=150;
                }
            }
        }
        // create ticket
        Ticket ticket=Ticket.builder()
                .totalAmount(total)
                .showTime(show.getShowTime())
                .showDate(show.getShowDate())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .bookedSeats(bookTicketRequest.getBookingSeats().toString())
                .user(user)
                .show(show)
        .build();

        // SAVE everything back to DB
        showSeatsRepository.saveAll(showSeatList);
        ticketRepository.save(ticket);
        List<Ticket> ticketList=show.getTicketList();
        ticketList.add(ticket);
        show.setTicketList(ticketList);
        showRepository.save(show);
        // return ticket response DTO
        TicketResponse ticketResponse=generateTicket(ticket.getTicketId());
        return ticketResponse;
    }
    public TicketResponse generateTicket(String ticketId){
        Ticket ticket=ticketRepository.findById(ticketId).get();
        TicketResponse ticketResponse=TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .movieName(ticket.getMovieName())
                .theaterName(ticket.getTheaterName())
                .totalAmount(ticket.getTotalAmount())
                .showDate(ticket.getShowDate())
                .showTime(ticket.getShowTime())
                .build();
        return ticketResponse;
    }
}
