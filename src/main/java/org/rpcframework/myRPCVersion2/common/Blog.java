package org.rpcframework.myRPCVersion2.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 15:48
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {
    private Integer id;
    private Integer userId;
    private String title;
}
