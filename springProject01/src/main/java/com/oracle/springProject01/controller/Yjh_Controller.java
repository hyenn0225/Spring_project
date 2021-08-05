package com.oracle.springProject01.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.springProject01.model.Post;
import com.oracle.springProject01.model.Reply;
import com.oracle.springProject01.service.paging.Paging;
import com.oracle.springProject01.service.yjhService.BoardCategoryService;
import com.oracle.springProject01.service.yjhService.MemberService;
import com.oracle.springProject01.service.yjhService.PostService;
import com.oracle.springProject01.service.yjhService.ReplyService;

@Controller
public class Yjh_Controller {

	@Autowired
	private PostService ps;

	@Autowired
	private BoardCategoryService bcs;

	@Autowired
	private MemberService ms;
	
	@Autowired
	private ReplyService rs;
	
//	겟방식 게시물 리스트 불러오기
	@RequestMapping(value = "/post/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryGet(Integer bt_num, Integer bc_num, Post post, String currentPage, Model model) {
		System.out.println("Yjh_Controller categoryGet Start...");
		int total = 0;
//		유형번호만 눌렀을때 카테고리값을 0으로
		if (bc_num == null)
			bc_num = 0;
//		게시물 갯수를 가져오기
		total = ps.total(bt_num, bc_num);
		System.out.println("Yjh_Controller categoryGet total->" + total);
//		페이징 처리
		Paging pg = new Paging(total, currentPage);
		post.setStart(pg.getStart());
		post.setEnd(pg.getEnd());
//		게시물 리스트
		List<Post> listPost = ps.listPost(post);
		for(Post post1 : listPost ) {
			System.out.println("Yjh_Controller categoryGet post1.getP_title()->"+post1.getP_title());
		}
		System.out.println("Yjh_Controller String list() listPost.size()->" + listPost.size());
		model.addAttribute("total", total);
		model.addAttribute("listPost", listPost);
		model.addAttribute("pg", pg);
		model.addAttribute("bt_num", bt_num);
		model.addAttribute("bc_num", bc_num);
		return "post/category";
	}

//	모임/클래스 개설하기 버튼
	@RequestMapping(value = "/post/add")
	public String add(HttpServletRequest request, Model model) {
		System.out.println("Yjh_Controller void add() start...");
		String sessionID =  (String) request.getSession().getAttribute("sessionID");
		model.addAttribute("sessionID",sessionID);
		return "post/add";
	}

//	게시물 작성  폼
	@GetMapping(value = "/post/register")
	public String register(HttpServletRequest request, int bt_num, Model model) {
		System.out.println("Yjh_Controller String register Start...");
//		섹션아이디
		String sessionID =  (String) request.getSession().getAttribute("sessionID");
//		섹션아이디의 정보가져오기
		Post post = ps.registerMember(sessionID);
//		게시물번호
		int p_num = 0;
//		페이지넘
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		model.addAttribute("p_num", p_num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("sessionID",sessionID);
		model.addAttribute("post",post);
		model.addAttribute("bt_num", bt_num);
		System.out.println("bt_num->" + bt_num);
		return "post/register";
	}

//	게시물 작성
	@PostMapping(value = "/post/insert")
	public String postInsert(Post post, Model model)  {
//		public String postInsert(HttpServletRequest request, MultipartFile p_img, Model model) throws IOException {
		System.out.println("Yjh_Controller String postInsert start...");
		System.out.println("post.p_cost: " + post.getP_cost());
		System.out.println(post.getP_starttime());
//		uploadPath = 파일경로지정
//		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
//		System.out.println("originalName : " + p_img.getOriginalFilename());
//		System.out.println("size : " + p_img.getSize());
//		System.out.println("contentType : " + p_img.getContentType());
//		System.out.println("uploadPath : " + uploadPath);
//		String savedName = uploadFile(p_img.getOriginalFilename(), p_img.getBytes(), uploadPath);
//		post.setP_img(p_img.getOriginalFilename());
//		System.out.println("saveName : " + savedName);
//		model.addAttribute("savedName", savedName);
		int result = ps.postInsert(post);
		System.out.println("Yjh_Controller postInsert result->" + result);
		if (result > 0) {
			return "forward:/post/category";
		} else {
			model.addAttribute("msg", "바보");
			return "forward:add";
		}
//		return "forward:/post/category";
//		return "/post/kkk";
	}
	
//	게시물 작성
//	@PostMapping(value = "/post/insert")
//	public String postInsert(HttpServletRequest request, MultipartFile p_img, Model model)  {
//		public String postInsert(HttpServletRequest request, MultipartFile p_img, Model model) throws IOException {
//		System.out.println("Yjh_Controller String postInsert start...");
//		System.out.println("post.p_cost: " + post.getP_cost());
//		System.out.println(post.getP_starttime());
////		uploadPath = 파일경로지정
//		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
//		System.out.println("originalName : " + p_img.getOriginalFilename());
//		System.out.println("size : " + p_img.getSize());
//		System.out.println("contentType : " + p_img.getContentType());
//		System.out.println("uploadPath : " + uploadPath);
//		String savedName = uploadFile(p_img.getOriginalFilename(), p_img.getBytes(), uploadPath);
//		post.setP_img(p_img.getOriginalFilename());
//		System.out.println("saveName : " + savedName);
//		model.addAttribute("savedName", savedName);
//		int result = ps.postInsert(post);
//		System.out.println("Yjh_Controller postInsert result->" + result);
	//		return "forward:/post/category";
//		return "/post/kkk";
//	}
	
//	private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws IOException {
//		System.out.println("UploadController String uploadFile start...");
////		UUID = 범용 고유 식별자는 소프트웨어 구축에 쓰이는 식별자 표준으로, 개방 소프트웨어 재단이 분산 컴퓨팅 환경의 일부로 표준화하였다
//		UUID uid = UUID.randomUUID();
////		requestPath = requestPath + "/resources/image";
//		System.out.println("uploadPath->" + uploadPath);
////		Directory 생성
//		File fileDirectory = new File(uploadPath);
////		fileDirectory 가 없으면 만들어준다
//		if (!fileDirectory.exists()) {
////			mkdirs = make directory의 약자
//			fileDirectory.mkdirs();
//			System.out.println("업로드용 폴더 생성 : " + uploadPath);
//		}
//		String savedName = uid.toString() + "_" + originalName;
//		
//		// savedName을 DB 에다가 원하는 Table에 원하는 항목에 저장 
//		File target = new File(uploadPath, savedName);
//		FileCopyUtils.copy(fileData, target); // org.springframework.util.FileCopyUtils
//
//		return savedName;
//	}

//	게시물 보기
	@RequestMapping(value = "/post/postListDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public String postListDetail(Integer bt_num, Integer bc_num, Integer p_num, Model model, HttpServletRequest request) {
		System.out.println("Yjh_Controller String postListDetail start...");
//		섹션아이디
		String sessionID =  (String) request.getSession().getAttribute("sessionID");		
//		게시물 리스트
		Post post = ps.postListDetail(bt_num, bc_num, p_num);
		System.out.println("Yjh_Controller postListDetail post->" + post);
//		댓글 리스트
		List<Reply> replyList = rs.postReplyList(bt_num, bc_num, p_num);
		System.out.println("Controller postReplyList done");
//		Reply reply = rs.postReplyList(bt_num, bc_num, p_num);
		int r_num = 0, r_rate = 0, r_indent = 0, r_group = 0, r_level = 0;
		model.addAttribute("sessionID",sessionID);
		model.addAttribute("r_num",r_num);
		model.addAttribute("r_rate",r_rate);
		model.addAttribute("r_indent",r_indent);
		model.addAttribute("r_group",r_group);
		model.addAttribute("r_level",r_level);
		model.addAttribute("post", post);	
		model.addAttribute("reply",replyList);
		return "post/contents";
	}
	
//	선택한 게시물 내용 수정 뷰단
	@RequestMapping(value = "/post/postListUpdateView", method = { RequestMethod.GET, RequestMethod.POST })
	public String postListUpdateView(Integer bt_num, Integer bc_num, Integer p_num, Model model) {
		System.out.println("Yjh_Controller String postListUpdateView strat...");
		System.out.println("Yjh_Controller postListUpdateView bt_num->"+bt_num);
		System.out.println("Yjh_Controller postListUpdateView bc_num->"+bc_num);
		System.out.println("Yjh_Controller postListUpdateView p_num->"+p_num);
		Post post = ps.postListDetail(bt_num, bc_num, p_num);
		model.addAttribute("post",post);
		return "post/updateContents";
	}
	
//	게시물 수정하기
	@RequestMapping(value = "/post/postListUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public String postListUpdate(Post post, Model model) {
		System.out.println("Yjh_Controller String postListUpdate start...");
		System.out.println("Yjh_Controller String postListUpdate post.getP_cost()->"+post.getP_cost());
		System.out.println("Yjh_Controller String postListUpdate post.getBt_num()->"+post.getBt_num());
		System.out.println("Yjh_Controller String postListUpdate post.getBc_num()->"+post.getBc_num());
		System.out.println("Yjh_Controller String postListUpdate post.getP_num()->"+post.getP_num());
		int result = ps.postListUpdate(post);
		System.out.println("Yjh_Controller String postListUpdate result->"+result);
		model.addAttribute("post",post);
		return "forward:/post/postListDetail";
	}
	
//	게시물 삭제하기
	@RequestMapping(value = "/post/postDelete", method = { RequestMethod.GET, RequestMethod.POST })
	public String postDelete(Integer bt_num, Integer bc_num, Integer p_num) {
		System.out.println("Yjh_Controller String postDelete start...");
		System.out.println("Yjh_Controller postDelete bt_num->"+bt_num);
		System.out.println("Yjh_Controller postDelete bc_num->"+bc_num);
		System.out.println("Yjh_Controller postDelete p_num->"+p_num);
		int result = ps.postDelete(bt_num, bc_num, p_num);
		System.out.println("Yjh_Controller String postDelete result->"+result);
		return "redirect:/main/main";
	}
	
//	댓글 작성
	@RequestMapping(value = "/reply/replyInsert", method = { RequestMethod.GET, RequestMethod.POST })
	public String replyInsert(Reply reply, Model model) {
		System.out.println("Yjh_Controller String replyInsert start...");
		int result = rs.replyInsert(reply);
		System.out.println("Yjh_Controller postInsert result->" + result);
		if (result > 0) {
//			model.addAttribute("reply",reply);
			return "forward:/post/postListDetail";
		} else {
			model.addAttribute("msg", "바보");
			return "forward:add";
		}
	}
	
//	댓글 삭제
	@RequestMapping(value = "/reply/replyDelete", method = { RequestMethod.GET, RequestMethod.POST })
	public String replyDelete(Integer bt_num, Integer bc_num, Integer p_num, Integer r_num, HttpServletRequest request) {
		System.out.println("Yjh_Controller replyDelete strat...");
		System.out.println("Yjh_Controller replyDelete bt_num->"+bt_num);
		System.out.println("Yjh_Controller replyDelete bc_num->"+bc_num);
		System.out.println("Yjh_Controller replyDelete p_num->"+p_num);
		System.out.println("Yjh_Controller replyDelete r_num->"+r_num);
		int result = rs.replyDelete(bt_num, bc_num, p_num, r_num);
		return "forward:/post/postListDetail";
	}
	
//	대댓글 등록
	@RequestMapping(value = "/reply/replyReplyInsert", method = { RequestMethod.GET, RequestMethod.POST })
	public String replyReplyInsert(Reply reply, HttpServletRequest request) {
		System.out.println("Yjh_Controller replyReplyInsert start...");
		System.out.println("Yjh_Controller replyDelete reply.getR_level->"+reply.getR_level());
		System.out.println("Yjh_Controller replyDelete getR_indent()->"+reply.getR_indent());
		System.out.println("Yjh_Controller replyDelete getR_group()->"+reply.getR_group());
		int result = rs.replyReplyInsert(reply);
		return "forward:/post/postListDetail";
	}

}
