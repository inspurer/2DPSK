/**
 * 原创声明:csu_xiaotao@163.com
 */
package handler;

/**
 * 新建类声明
 * @author csu_xiaotao
 *<a href = "https://github.com/inspurer">月小水长</a>
 * 下午7:19:23
 * 2018年7月4日
 */
public class handler {
	Code2Wave cw;
	Wave2Code wc;
	public handler() {
		cw = new Code2Wave();
		wc = new Wave2Code(cw.getCodes().length);
		Wave2Code.b();
		Wave2Code.c(cw.getY());
		Wave2Code.d();
	}
}
