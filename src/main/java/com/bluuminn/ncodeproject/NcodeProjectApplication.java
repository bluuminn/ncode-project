package com.bluuminn.ncodeproject;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.bluuminn.ncodeproject.domain.feed.FeedRepository;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.stream.IntStream;

@EnableJpaAuditing
@SpringBootApplication
public class NcodeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NcodeProjectApplication.class, args);
    }

    @Bean
    public Mapper mapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }

    // 더미 데이터 초기화
    @Bean
    public CommandLineRunner initData(FeedRepository feedRepository) {
        return args -> IntStream.rangeClosed(1, 154).forEach(i -> {
            Feed feed = Feed.builder()
                    .mdImages("md이미지" + i)
                    .mdName("md이름" + i)
                    .contents("피드내용" + i)
                    .build();

            feedRepository.save(feed);
        });
    }
}
