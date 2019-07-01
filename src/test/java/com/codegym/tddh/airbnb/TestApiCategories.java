package com.codegym.tddh.airbnb;

import com.codegym.tddh.airbnb.api.ApiHouseController;
import com.codegym.tddh.airbnb.api.CategoriesApi;
import com.codegym.tddh.airbnb.model.Categories;
import com.codegym.tddh.airbnb.service.CategoriesHouseService;
import com.codegym.tddh.airbnb.service.HouseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestApiCategories {

    private MockMvc mockMvc;

    @Mock
    private CategoriesHouseService categoriesHouseService;

    @InjectMocks
    private CategoriesApi categoriesApiController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(categoriesApiController)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void test_get_all_success() throws Exception {
        List<Categories> categories = Arrays.asList(
                new Categories("nha"),
                new Categories("hang"));
        when(categoriesHouseService.findAll()).thenReturn(categories);
        mockMvc.perform(get("/api/auth/categories"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_get_by_id_success() throws Exception {
        Optional<Categories> categories = Optional.of(new Categories("NhaTo"));
        when(categoriesHouseService.findById((long) 1)).thenReturn(categories);
        mockMvc.perform(get("/api/auth/categories/{id}", 1))
                .andExpect(status().isOk());
    }

//    @Test
//    public void test_update_categories_success() throws Exception {
//        Categories categories = new Categories("NhaKhung");
//        when(categoriesHouseService.findById((long) 1)).thenReturn(Optional.of(categories));
//        doNothing().when(categoriesHouseService).save(categories);
//        mockMvc.perform(put("/api/auth/categories/{id}", (long) 1))
//                .andExpect(status().isOk());
//    }

//    @Test
//    public void test_delete_categories_success() throws Exception {
//        Categories categories = new Categories("NhaKhung");
//        when(categoriesHouseService.findById((long) 1)).thenReturn(Optional.of(categories));
//        doNothing().when(categoriesHouseService).remove((long) 1);
//        mockMvc.perform(delete("/api/auth/categories/{id}", (long) 1))
//                .andExpect(status().isOk());
//    }

}
