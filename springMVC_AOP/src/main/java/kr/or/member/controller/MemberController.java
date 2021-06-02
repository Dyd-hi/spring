package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller
public class MemberController {
	//자주쓸거라 전역변수 선언
	@Autowired
	private MemberService service;

   public MemberController() {
      super();
      System.out.println("MemberController 생성완료");
   }
   
   @RequestMapping(value="/login.do")
   		//1.값추출
   public String login(Member m,HttpSession session,Model model) {
	   //2.로직처리
	   Member member = service.selectOneMember(m);
	   if(member!= null) {
		   session.setAttribute("m", member);
		   model.addAttribute("msg","로그인 성공");
	   }else {
		   model.addAttribute("msg","아이디 또는 비밀번호를 확인해주세ㅛㅇ");
	   }
	   model.addAttribute("loc","/");
	   return "common/msg";
   }
 
   @RequestMapping(value="/joinFrm.do")
   public String joinFrm() {
	   //이렇게만 적어도 저절로 경로 설정되서 이동
	   return "member/joinFrm";
   }
   
   @RequestMapping(value="/join.do")
   public String join(Member m,Model model) {
	   int result = service.insertMember(m);
	   if(result >0) {
		   model.addAttribute("msg","회원가입 성공");
	   }else {
		   model.addAttribute("msg","회원가입 실패");
	   }
	   model.addAttribute("loc","/");
	   return "common/msg";
   }
   
   @RequestMapping(value="/logout.do")
   public String logout(Member m,Model model,HttpSession session) {
	   if(session !=null) {
		   session.invalidate();
		   model.addAttribute("msg","로그아웃하셨습니다");
	   }
	   model.addAttribute("loc","/");
	   return "common/msg";
	  
	   //return "redirect:/";  msg 필요없이 바로 갈경우
   }
   
   @RequestMapping(value="/searchFrm.do")
   public String searchFrm() {
      return "member/searchFrm";
   }
   
   @RequestMapping(value="idSearch.do")
   //1.Member m으로 한번에 받거나
   //2.name값 하나하나 받을수도 있다
   public String idSearch(Member m,Model model) {
	   Member member = service.searchId(m);
	   if(member != null) {
		   model.addAttribute("msg","아이디는["+member.getMemberId()+"] 입니다.");
	   }else {
		   model.addAttribute("msg","정보를 조회할수 없습니다.");
	   }
	   model.addAttribute("loc","/");
	   return "common/msg";
   }
   
   //아이디/비번 찾기
   @RequestMapping(value="/pwSearch.do")
   public String pwSearch(Member m, Model model) {
	   Member member = service.searchPw(m);
	   if(member != null) {
		   model.addAttribute("msg","비밀번호는["+member.getMemberPw()+"] 입니다.");
	   }else {
		   model.addAttribute("msg","정보를 조회할수 없습니다.");
	   }
	   model.addAttribute("loc","/");
	   return "common/msg";
   }
   
   
   //탈퇴
   @RequestMapping(value="/deleteMember.do")
   public String deleteMember(String memberId,HttpSession session,Model model) {
	   int result = service.deleteMember(memberId);
	   if(result>0) {
		   session.invalidate();
		   model.addAttribute("msg","탈퇴되셨습니다.");
	   }else {
		   model.addAttribute("msg","탈퇴 실패.");
	   }
	   model.addAttribute("loc","/");
	   return "common/msg";
   }
   
   @RequestMapping(value="/mypage.do")
   public String mypage(String memberId,Model model) {
	   Member member = service.selectOneMember(memberId);
		   model.addAttribute("member",member);
		   return "member/mypage";
   }
   
   @RequestMapping(value="/updatemember.do")
   public String updatemember(Member m,Model model) {
	   int result = service.updateMember(m);
	   return "redirect:/mypage.do?memberId="+m.getMemberId();
   }
   
   @RequestMapping(value="/allMember.do")
   public String allMember(Model model ) {
	   ArrayList<Member> list = service.selectAllMember();
	   model.addAttribute("list",list);
	   return "member/allMember";
   }
   
   @RequestMapping(value="/allMemberCount.do")
   public String allMemberCount(Model model) {
	   int result = service.allMemberCount();
	   model.addAttribute("msg","총 회원의 수는["+result+"]입니다.");
	   model.addAttribute("loc","/");
	   return "common/msg";
   }
   
   @RequestMapping(value="/checkPw.do")
   public String checkPw() {	   	  
	   return "member/checkPw";
   }
   
   @RequestMapping(value="/updatePw.do")
   public String updatePw() {
	   return "member/updatePw";
   }
}






