package radoslawlewandowski.portal.Service;

import org.springframework.stereotype.Service;
import radoslawlewandowski.portal.DTO.AddressDto;
import radoslawlewandowski.portal.Model.Address;

@Service
public class AddressService {

    public Address getAddress(AddressDto addressDto) {
        return Address.builder()
                .addressLine1(addressDto.getAddressLine1())
                .addressLine2(addressDto.getAddressLine2())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .zipCode(addressDto.getZipCode())
                .build();

    }

    public AddressDto getAddressDto(Address address) {
        return AddressDto.builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }
}
