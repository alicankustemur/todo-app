package io.github.alicankustemur.todoapp.service.impl;

import io.github.alicankustemur.todoapp.service.InitializeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by alicankustemur on 13/06/2017.
 */

@Service
@Profile("default")
public class InitializeServiceActiveProfilesImpl implements InitializeService {
    @Override
    public void init() {
        System.out.println("Application is started!");
    }
}
