package com.codegym.tddh.airbnb;
import com.codegym.tddh.airbnb.api.ApiHouseController;
import com.codegym.tddh.airbnb.model.Categories;
import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.service.HouseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestApiHouse {

    private MockMvc mockMvc;

    @Mock
    private HouseService houseService;

    @InjectMocks
    private ApiHouseController apiHouseController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(apiHouseController)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void test_get_all_success_house() throws Exception {
        Categories categories = new Categories("nha");
        Date date = new Date(2014-02-05);
        User user = new User(	"firstName", "lastName", date, "hell1@gmail.com", "123456789");
        List<House> houses = Arrays.asList(
                new House("Hoang", "HaNoi", 1, 1, "ok", 12.1),
                new House("Trung", "HCM", 2, 2, "ok", 124.1));
        when(houseService.findAll()).thenReturn(houses);
        mockMvc.perform(get("/api/auth/houses")).andExpect(status().isOk());
    }

    @Test
    public void test_get_by_id_success_house() throws  Exception {
        Categories categories = new Categories("nha");
        Date date = new Date(2014-02-05);
        User user = new User(	"firstName", "lastName", date, "hell1@gmail.com", "123456789");
        House houses = new House("Hoang", "HaNoi", 1, 1, "ok", 12.1 );
        when(houseService.findById((long) 1)).thenReturn(houses);
        mockMvc.perform(get("/api/auth/house/{id}",1)).andExpect(status().isOk());
    }

    //    @Test
//    public void test_update_house_success() throws Exception  {
//        Categories categories = new Categories("nha");
//        Date date = new Date(2014-02-05);
//        User user = new User(	"firstName", "lastName", date, "hell1@gmail.com", "123456789");
//        House houses = new House("Hoang", "HaNoi", 1, 1, "ok", 12.1 );
//        houses.setCategories(categories);
//        houses.setUser(user);
//        when(houseService.findById(houses.getId())).thenReturn(houses);
//        doNothing().when(houseService).save(houses);
//        mockMvc.perform(put("/api/auth/house/{id}", houses.getId())
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//    }

//    @Test
//    public void test_delete_house_success() throws Exception {
//        House houses = new House("Hoang", "HaNoi", 1, 1, "ok", 12.1 );
//        when(houseService.findById((long) 1)).thenReturn(houses);
//        doNothing().when(houseService).remove((long) 1);
//        mockMvc.perform(delete("/api/auth/house/{id}",1)).andExpect(status().isOk());
//    }


}

