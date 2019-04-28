/**
 * 12/24/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.rate_model;

import ir.aliloc.api.core.user_rate.RateType;

interface IRateModelDAO {

    RateModel getRateModelByType(RateType rateType) throws Exception;

}
