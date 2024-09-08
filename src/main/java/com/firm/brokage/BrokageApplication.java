package com.firm.brokage;

import com.firm.brokage.entity.Asset;
import com.firm.brokage.enums.Currency;
import com.firm.brokage.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@RequiredArgsConstructor
@SpringBootApplication
public class BrokageApplication implements CommandLineRunner {

	private final AssetRepository assetRepository;

	public static void main(String[] args) {
		SpringApplication.run(BrokageApplication.class, args);
	}

	@Override
	public void run(String... args) {
		assetRepository.saveAll(Arrays.asList(
				Asset.builder().customerId(1L).assetName(Currency.TRY.name()).size(1000).usableSize(1000).build(),
				Asset.builder().customerId(2L).assetName(Currency.TRY.name()).size(2000).usableSize(2000).build(),
				Asset.builder().customerId(3L).assetName(Currency.TRY.name()).size(3000).usableSize(5000).build(),
				Asset.builder().customerId(4L).assetName(Currency.TRY.name()).size(4000).usableSize(2000).build(),

				Asset.builder().customerId(1L).assetName("Home").size(1).usableSize(1).build(),
				Asset.builder().customerId(1L).assetName("Car").size(1).usableSize(1).build(),

				Asset.builder().customerId(2L).assetName("Home").size(2).usableSize(2).build(),
				Asset.builder().customerId(2L).assetName("Car").size(2).usableSize(2).build(),

				Asset.builder().customerId(3L).assetName("Home").size(2).usableSize(2).build(),
				Asset.builder().customerId(3L).assetName("Car").size(2).usableSize(2).build()
		));
	}
}
