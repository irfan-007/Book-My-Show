package com.bookMyShow.Book.My.Show.controllers;

import com.bookMyShow.Book.My.Show.requestDTOs.BookTicketRequest;
import com.bookMyShow.Book.My.Show.responseDTOs.TicketResponse;
import com.bookMyShow.Book.My.Show.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("book-ticket")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequest bookTicketRequest){
        TicketResponse res=ticketService.bookTicket(bookTicketRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("generate-ticket")
    public TicketResponse generateTicket(@RequestParam String ticketId){
        TicketResponse res=ticketService.generateTicket(ticketId);
    }
}
