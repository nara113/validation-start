package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {
    @PostMapping("/add")
    public Object add(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        return form;
    }
}
