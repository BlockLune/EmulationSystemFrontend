package com.njupt.epsm.metadata.section;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class M_Impact implements Serializable {

    public String confidentiality;

    public String integrity;

    public String availability;

}