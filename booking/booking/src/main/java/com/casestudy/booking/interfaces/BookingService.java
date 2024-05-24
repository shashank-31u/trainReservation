package com.casestudy.booking.interfaces;

import java.util.List;

import com.casestudy.booking.dto.BookingDTO;
import com.casestudy.booking.dto.BookingOutboundDTO;
import com.casestudy.booking.dto.ResponseDTO;
import com.casestudy.booking.dto.SeatDTO;
import com.casestudy.booking.dto.SeatOutboundDTO;
import com.casestudy.booking.model.SeatBooking;

public interface BookingService {

    public BookingOutboundDTO createBooking(BookingDTO bookingDTO);

    public SeatOutboundDTO saveBooking(BookingDTO bookingDTO);

    public List<SeatBooking> getSeatAvailability(SeatDTO seatDTO);

    public ResponseDTO cancelBooking(SeatDTO seatDTO, String pnr);

}
