package com.zonaut.sbreactive.extensions;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.atomic.AtomicBoolean;

public class DynamicPortDatabaseTestContainersExtension implements BeforeAllCallback {

    private static final AtomicBoolean started = new AtomicBoolean(false);

//    @DynamicPropertySource
//    public static void r2dbcProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.r2dbc.url", () -> container.getJdbcUrl().replace("jdbc", "r2dbc"));
//        registry.add("spring.r2dbc.username", () -> container.getUsername());
//        registry.add("spring.r2dbc.password", () -> container.getPassword());
//
//        registry.add("spring.liquibase.url", () -> container.getJdbcUrl());
//        registry.add("spring.liquibase.user", () -> container.getUsername());
//        registry.add("spring.liquibase.password", () -> container.getPassword());
//    }


    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!started.get()) {

            int containerPort = 5432;
            int localPort = 5434;
            DockerImageName postgres = DockerImageName.parse("postgres:13.2");
            GenericContainer<?> postgreDBContainer = new PostgreSQLContainer<>(postgres)
                .withDatabaseName("sb-reactive")
                .withUsername("postgres")
                .withPassword("password")
                .withReuse(true)
                .withExposedPorts(containerPort)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(localPort), new ExposedPort(containerPort)))
                ));

            postgreDBContainer.start();


            started.set(true);
        }

    }
}
