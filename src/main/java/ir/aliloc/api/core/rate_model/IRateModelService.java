/**
 * 12/24/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.rate_model;

import ir.aliloc.api.core.user_rate.RateType;

public interface IRateModelService {

    RateModelDTO getRateModelByType(RateType rateType) throws Exception;

    RateModel getMainObjectRateModelByType(RateType rateType) throws Exception;
}
