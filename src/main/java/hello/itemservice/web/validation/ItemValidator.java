package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // item == clazz
        // item == subItem (자식 클래스도 커버)
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required");
        }

        if (item.getPrice() == null || item.getPrice() < 1_000 || item.getPrice() > 1_000_000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }

        if (item.getQuantity() == null || item.getQuantity() < 0 || item.getQuantity() > 10_000) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int totalPrice = item.getPrice() * item.getQuantity();
            if (totalPrice < 10_000) {
                errors.reject("totalPriceMin", new Object[]{10000, totalPrice}, null);
            }
        }
    }
}
