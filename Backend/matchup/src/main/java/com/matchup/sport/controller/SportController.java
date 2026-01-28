package com.matchup.sport.controller;


import com.matchup.common.controller.CrudController;
import com.matchup.sport.dto.SportDTO;
import com.matchup.sport.entity.Sport;
import com.matchup.sport.mapper.SportMapper;
import com.matchup.sport.service.SportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/sports")
public class SportController extends CrudController<Sport, SportDTO, SportService, SportMapper> {
    public SportController(SportService service, SportMapper mapper) {
        super(service, mapper);
    }
}
