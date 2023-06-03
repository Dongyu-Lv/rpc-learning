package org.rpcframework.myRPCVersion1.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dongyu Lv
 * @create 2023-05-25 23:45
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String username;
    private boolean sex;
}