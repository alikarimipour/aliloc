/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.payment;

import ir.aliloc.api.core.enums.EPaymentState;
import lombok.Data;

@Data
public class PaymentDTO {
    private long id;
    private int amount;
    private long courseId;
    private long BookId;
    private String payKey;
    private String payUrl;
    private EPaymentState paymentState;
    private long date;
}
