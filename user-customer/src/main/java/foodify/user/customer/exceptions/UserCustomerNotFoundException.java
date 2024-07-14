package foodify.user.customer.exceptions;

import org.apache.coyote.BadRequestException;

public class UserCustomerNotFoundException extends BadRequestException {

    public UserCustomerNotFoundException(String message){
        super(message);
    }

}
