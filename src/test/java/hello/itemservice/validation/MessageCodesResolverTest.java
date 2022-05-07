package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

class MessageCodesResolverTest {
    MessageCodesResolver resolver = new DefaultMessageCodesResolver();

    @Test
    void object() {
        String[] codes = resolver.resolveMessageCodes("required", "item");
        Assertions.assertThat(codes).containsExactly("required.item", "required");
    }

    @Test
    void field() {
        String[] codes = resolver.resolveMessageCodes("required", "item", "itemName", String.class);
        Assertions.assertThat(codes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required");
    }
}
