package com.ecommerce.user.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(position = 3)
	@NotNull(message = "{Provide Product Id}")
	@Pattern(regexp = "[0-9]", message = "{Provide valid Product Id}")
	private String productId;
	@ApiModelProperty(position = 4)
	@NotNull(message = "{Provide Quantity}")
	@Pattern(regexp = "[0-9]", message = "{Provide valid Quantity}")
	private String quantity;
	@JsonIgnore
	private double totalPrice;

}
