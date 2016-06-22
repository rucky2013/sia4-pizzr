package cc.picc.commons;

/**
 * 视图资源接口，用于统一视图资源的格式，便于基于抽象进行调用。
 * 
 * @author lijinting01
 * 
 */
public interface ViewResource {
	/**
	 * 视图名称
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 视图的标题
	 * 
	 * @return
	 */
	String getTitle();
}
