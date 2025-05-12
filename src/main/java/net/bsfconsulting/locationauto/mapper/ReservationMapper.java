package net.bsfconsulting.locationauto.mapper;

import net.bsfconsulting.locationauto.dto.ReservationDto;
import net.bsfconsulting.locationauto.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDto reservationToReservationDto(Reservation reservation);
    Reservation reservationDtoToReservation(ReservationDto reservationDto);
}

