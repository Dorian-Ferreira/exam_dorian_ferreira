package fr.dorian_ferreira.exam.configuration;

import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Configuration
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private static final Faker faker = new Faker(Locale.FRANCE);

    @Override
    public void run(String... args) {

    }

}
