package top.harvie.ProjectTeam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@MapperScan("top.harvie.ProjectTeam.dao.mapper")
@EnableCaching
@EnableDiscoveryClient
public class ProjectTeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTeamApplication.class, args);
	}

}
