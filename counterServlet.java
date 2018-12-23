package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class counterServlet
 */
@WebServlet("/counterServlet")
public class counterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public counterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストの文字コード設定
		request.setCharacterEncoding("UTF-8");
		
		//カウンタ変数初期化
		int intCnt=0;
		//セッションを	생성
		//セッションが即にある場合は、新規に作成はしない。
		//セッションがない場合は、新規作成
		HttpSession session = request.getSession();
		
		//セッションにCNTがあるチェック(nullチェック)
		if(session.getAttribute("CNT")!=null)
		{
			//セッションからカウントを取得
			intCnt=(Integer)session.getAttribute("CNT");
		}
		
		//カウントアップボタンが押下時処理
		if(request.getParameter("CNTUP")!=null)
		{
			intCnt++;
			//セッションにカウントを保存
			session.setAttribute("CNT", intCnt);
		}
		//カウントダウンボタンが押下時処理
		else if(request.getParameter("CNTDOWN")!=null)
		{
			intCnt--;
			//セッションにカウントを保存
			session.setAttribute("CNT", intCnt);
		}
		//クリアボタンが押下時処理
		else if(request.getParameter("CLR")!=null)
		{
			//セッションからCNT削除
			session.removeAttribute("CNT");
		}
		//전송처리
		RequestDispatcher disp = request.getRequestDispatcher("result.jsp");
		disp.forward(request, response);
	}

}
