package com.light.springboot.domain.search;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 李振振
 * @version 1.0
 * @date 2021/3/26 9:44
 */
@Data
public class GoodInfo {
    @Getter
    @Setter
    private String goodId;

    @Getter
    @Setter
    private String goodType;

}
