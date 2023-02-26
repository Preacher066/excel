package in.uchiha.springbasic.service;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class RandomStringProvider implements Supplier<String> {

        private RandomStringGenerator errorCodeGenerator;

        public RandomStringProvider() {
            errorCodeGenerator = new RandomStringGenerator.Builder()
                    .withinRange('0', 'z')
                    .filteredBy(t -> t >= '0' && t <= '9', t -> t >= 'A' && t <= 'Z', t -> t >= 'a' && t <= 'z')
                    .build();
        }

        @Override
        public String get() {
            return errorCodeGenerator.generate(8);
        }

    }
