package com.robot.weixin.menu;

import com.robot.weixin.menu.model.Button;
import com.robot.weixin.menu.model.ComplexButton;
import com.robot.weixin.menu.model.Menu;
import com.robot.weixin.menu.model.ViewButton;

/**
 * 菜单管理器类
 * 
 */
public class MenuManager {
	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	public static Menu getMenu() {
		ViewButton btn21 = new ViewButton();
		btn21.setName("stock");
		btn21.setType("view");
		btn21.setUrl(Constants.WEBURL_KEY + Constants.WEBROOT + "/stock");

		ViewButton btn31 = new ViewButton();
		btn31.setName("关于我们");
		btn31.setType("view");
		btn31.setUrl(Constants.WEBURL_KEY + Constants.WEBROOT + "/about");

		ViewButton btn32 = new ViewButton();
		btn32.setName("意见建议");
		btn32.setType("view");
		btn32.setUrl(Constants.WEBURL_KEY + Constants.WEBROOT + "/suggest");

		ViewButton btn11 = new ViewButton();
		btn11.setName("主页");
		btn11.setType("view");
		btn11.setUrl(Constants.WEBURL_KEY + Constants.WEBROOT);

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("stock");
		mainBtn2.setSub_button(new Button[] { btn21 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多");
		mainBtn3.setSub_button(new Button[] { btn31, btn32 });

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn11, mainBtn2, mainBtn3 });

		return menu;
	}
}