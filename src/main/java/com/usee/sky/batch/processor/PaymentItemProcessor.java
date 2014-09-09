/**
 * 
 */
package com.usee.sky.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.usee.sky.batch.listener.MoneyNotEnoughException;
import com.usee.sky.model.Bill;
import com.usee.sky.model.PayRecord;

/**
 * @author kunrey
 * 
 */
@Component("payProcessor")
public class PaymentItemProcessor implements ItemProcessor<Bill, PayRecord>
{

	public PayRecord process(Bill item) throws Exception
	{
		if (item.getUser().getBalance() <= 0)
		{
			return null;
		}
		if (item.getUser().getBalance() >= item.getUnpaidFees())
		{
			// create payrecord
			PayRecord pr = new PayRecord();
			pr.setBill(item);
			pr.setPaidFees(item.getUnpaidFees());
			// update balance
			item.getUser().setBalance(
					item.getUser().getBalance() - item.getUnpaidFees());
			// update bill
			item.setPaidFees(item.getUnpaidFees());
			item.setUnpaidFees(0.0);
			item.setPayStatus(1);/* paid */
			return pr;
		} else
		{
			throw new MoneyNotEnoughException();
		}
	}
}
