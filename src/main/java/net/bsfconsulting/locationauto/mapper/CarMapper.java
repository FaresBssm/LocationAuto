package net.bsfconsulting.locationauto.mapper;

import net.bsfconsulting.locationauto.dto.CarDto;
import net.bsfconsulting.locationauto.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDto carToCarDto(Car car);
    Car carDtoToCar(CarDto carDto);
}
