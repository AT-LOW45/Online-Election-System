package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Validator<T> {
    final private T type;
    final private List<Predicate<T>> checks;
    final private Map<String, String> errorMessageMapping;
    
    public Validator(T type) {
        this.type = type;
        this.checks = new ArrayList<>();
        this.errorMessageMapping = new HashMap<>();
    }
    
    public <T> Validator<T> of(T type) {
        return type == null ? null : new Validator<>(type);
    }
    
    public Validator<T> validate(Predicate<T> checkValid, String field, String errorMessage) {
        if(!checkValid.test(type)) {
            this.errorMessageMapping.put(field, errorMessage);
        }
        this.checks.add(checkValid);
        return this;
    }
   
    public boolean getValidationResult() {
        return this.checks.stream().reduce(Predicate::and).orElse(t -> false).test(type);
    }
    
    public Map<String, String> getErrorMessages() {
        return this.errorMessageMapping;
    }
}
