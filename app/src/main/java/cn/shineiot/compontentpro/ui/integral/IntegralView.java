package cn.shineiot.compontentpro.ui.integral;

import cn.shineiot.base.bean.Coin;
import cn.shineiot.base.bean.Integral;
import cn.shineiot.base.module.BaseView;

/**
 * @author GF63
 */
public interface IntegralView extends BaseView {
	void succeessMyCoin(Coin coin);
	void successListCoin(Integral integral);
}
