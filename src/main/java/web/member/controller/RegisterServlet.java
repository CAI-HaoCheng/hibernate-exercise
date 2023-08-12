package web.member.controller;

import core.util.CommonUtil;
import web.member.entity.Member;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/member/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;

	@Override
	public void init() throws ServletException {
		service = CommonUtil.getBean(getServletContext(), MemberService.class);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Member member = json2Pojo(request, Member.class);
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			writePojo2Json(response, member);
			return;
		}
		
		member = service.register(member);
		writePojo2Json(response, member);
	}
}
