<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/contents.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/main/header.jsp"%>
	<!-- **MAIN START** -->
	<main>
		<!-- main -->
		<div class="contents_wrap">
			<div>
				<div class="cont_info">
					<div class="offeror">
						<div class="con_img">
							<img src="${pageContext.request.contextPath}/img/goya.jpg">
						</div>
						<div class="offeror_info">
							<div class="offer_title">개설자정보</div>
							<div class="info_detail">
								<div class="offer_item">
									<div class="offer_img">
										<img src="${pageContext.request.contextPath}/img/01.jpg">
									</div>
									<h2>${post.m_name }</h2>
								</div>
								<div class="offer_item">
									<div class="item_title">이 메 일</div>
									<div class="item_detail">${post.m_id }</div>
								</div>
								<div class="offer_item">
									<div class="item_title">전화번호</div>
									<div class="item_detail">${post.m_tel }</div>
								</div>
							</div>
							<div class="offer_option">*로그인 후 정보를 볼 수 있습니다.</div>
						</div>
					</div>
					<div class="a">
						<div class="con_tit">${post.p_title }</div>
						<div class="con_sub">${post.p_intro }</div>
						<div class="con_hash">
							<a>#스프링</a> <a>#뿌뿌</a>
						</div>
						<div class="meet_info">
							<div class="meet_title">모임기간</div>
							<div class="meet_detail">
								2021.7.11 (일) 08:50 ~ 12:00 <input type="hidden">
							</div>
						</div>
						<div class="meet_info">
							<div class="meet_title">모임장소</div>
							<div class="meet_detail">
								${post.p_loc } <input type="hidden">
							</div>
						</div>
						<div class="meet_info">
							<div class="meet_title">그룹명</div>
							<div class="meet_detail">
								${post.p_gname } <input type="hidden">
							</div>
						</div>
						<div class="btn_section">
							<button>신청하기</button>
							<button type="button" 
									onclick="location.href='${pageContext.request.contextPath}/post/postListUpdateView?bt_num=${post.bt_num }&bc_num=${post.bc_num }&p_num=${post.p_num}'">
									수정하기
							</button>
							<button type="button" 
									onclick="location.href='${pageContext.request.contextPath}/post/postDelete?bt_num=${post.bt_num }&bc_num=${post.bc_num }&p_num=${post.p_num}'">
									삭제하기
							</button>
							<button>신청완료</button>
							<button>찜</button>
						</div>
					</div>
				</div>
				<div class="con_detail">
					<div class="con_nav">
						<div class="det_nav">상세정보</div>
						<div class="det_nav">지도</div>
						<div class="det_nav">문의/기대평</div>
						<div class="det_nav">문의/리뷰</div>
						<div class="det_nav">참여/취소안내</div>
					</div>
					<div class="con_item">
						<div class="item_tit">상세정보</div>
						<div class="item_det">${post.p_info }</div>
					</div>
					<div class="con_item">
						<div class="item_tit">지도</div>
						<div class="item_det">
							<textarea>임시지도위치</textarea>
						</div>
					</div> 
					<div class="con_item">
<!-- 						댓글 등록하는 곳 -->
						<form action="${pageContext.request.contextPath}/reply/replyInsert" method="post">
							<input type="hidden" name="bt_num" value="${post.bt_num }">
							<input type="hidden" name="bc_num" value="${post.bc_num }">
							<input type="hidden" name="p_num" value="${post.p_num }">
							<input type="hidden" name="r_num" value="${r_num }">
							<input type="hidden" name="m_id" value="${sessionID }">
							<input type="hidden" name="r_indent" value="${r_indent }">
							<input type="hidden" name="r_group" value="${r_group }">
							<input type="hidden" name="r_group" value="${r_level }">
							<div class="item_tit">문의/기대평</div>
							<div class="item_det">
								<a class="photo"> <img src="${pageContext.request.contextPath}/img/01.jpg">
								</a>
								<c:if test="${sessionID != null}">
									<textarea rows="5" cols="70" name="r_info"></textarea>
									<button type="submit">등록</button>
									평점
									<select name="r_rate">
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</c:if>
								<c:if test="${sessionID == null}">
									<a href="${pageContext.request.contextPath}/member/login">
										<textarea rows="5" cols="70" name="r_info"></textarea>
									</a>
								</c:if>
<!-- 								<textarea rows="5" cols="70" name="r_info"></textarea> -->
<!-- 								<button type="submit">등록</button> -->
								<input type="checkbox" name="r_openclose" value="Y" checked="checked">공개
								<input type="checkbox" name="r_openclose" value="N">비공개
							</div>
						</form>
					</div>
					<hr>
<!-- 					댓글들 모임 -->
					<div class="con_item">
						<div class="item_tit">댓글들</div>
						<c:forEach var="reply" items="${reply }">
						<div class="item_det">
<!-- 							대댓글들 다른이미지 보여주는곳 -->
							<c:if test="${reply.r_level > 0 }">
								<a class="photo">
								<img src="${pageContext.request.contextPath}/img/re.gif" width="${reply.r_level*10 }">
								</a>
							</c:if>
<!-- 							댓글들 이미지 -->
							<c:if test="${reply.r_level == 0 }">
								<a class="photo">
								<img src="${pageContext.request.contextPath}/img/01.jpg">
								</a>
							</c:if>
							${reply.m_name }
							${reply.m_tel }
							${reply.r_writedate }
							${reply.r_num }
<!-- 							댓글이 비공개일때 글쓴사람이랑 관리자만 보기 -->
							<c:if test="${reply.r_openclose == 'N'}" >
					            <c:choose>
					                <c:when test="${reply.m_id == sessionID || sessionID == 'aaaaaa@aaaaaa.com'}">
					                    <textarea rows="5" cols="70" name="r_info" readonly="readonly">${reply.r_info }</textarea>
					                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/reply/replyDelete?bt_num=${reply.bt_num }&bc_num=${reply.bc_num }&p_num=${reply.p_num}&r_num=${reply.r_num}'">삭제</button>
<!-- 							           	 댓글이 비공개일때 답글쓰는 로직 -->
							            <div id='dis' style="display: none;">
		           							<form action="${pageContext.request.contextPath}/reply/replyReplyInsert" method="post">
												<input type="hidden" name="bt_num" value="${reply.bt_num }">
												<input type="hidden" name="bc_num" value="${reply.bc_num }">
												<input type="hidden" name="p_num" value="${reply.p_num }">
												<input type="hidden" name="r_num" value="${reply.r_num }">
												<input type="hidden" name="m_id" value="${sessionID }">
												<input type="hidden" name="r_indent" value="${r_indent }">
												<input type="hidden" name="r_group" value="${reply.r_group }">
												<input type="hidden" name="r_group" value="${r_level }">
												<div class="item_tit">문의/기대평</div>
												<div class="item_det">
													<a class="photo"> <img src="${pageContext.request.contextPath}/img/01.jpg"></a>
													<textarea rows="5" cols="70" name="r_info"></textarea>
													<button type="submit">등록</button>
												</div>
											</form>
							            </div>
										<c:if test="${reply.r_level == 0 }">
											<button id='show' onclick="dis()">답변쓰기</button>
										</c:if>
					                </c:when>
					                <c:otherwise>비밀글은 작성자와 관리자만 볼 수 있습니다.</c:otherwise>
					            </c:choose>
					        </c:if>
<!-- 					       	댓글 공개일떄 답글 쓰는 로직 -->
					        <c:if test="${reply.r_openclose eq 'Y'}" >
					            <textarea rows="5" cols="70" name="r_info" readonly="readonly">${reply.r_info }</textarea>
					            
					            <div id='dis${reply.r_num }' style="display: none;">
           							<form action="${pageContext.request.contextPath}/reply/replyReplyInsert" method="post">
										<input type="hidden" name="bt_num" value="${reply.bt_num }">
										<input type="hidden" name="bc_num" value="${reply.bc_num }">
										<input type="hidden" name="p_num" value="${reply.p_num }">
										<input type="hidden" name="r_num" value="${reply.r_num }">
										<input type="hidden" name="m_id" value="${sessionID }">
										<input type="hidden" name="r_indent" value="${r_indent }">
										<input type="hidden" name="r_group" value="${reply.r_group }">
										<input type="hidden" name="r_group" value="${r_level }">
										<div class="item_tit">문의/기대평</div>
										<div class="item_det">
											<a class="photo"> <img src="${pageContext.request.contextPath}/img/01.jpg"></a>
											<textarea rows="5" cols="70" name="r_info"></textarea>
											<button type="submit">등록</button>
										</div>
									</form>
					            </div>
								<c:if test="${sessionID != null  }">
									<c:if test="${reply.r_level == 0 }">
										<button id='show' onclick="dis(${reply.r_num})">답변쓰기</button>
									</c:if>
<!-- 									<button id='show' onclick="dis()">답변쓰기</button> -->
								</c:if>
								<c:if test="${sessionID == null}">
									<a href="${pageContext.request.contextPath}/member/login">
									<button id='show'>답변</button></a>
								</c:if>
<!-- 								<button id='show' onclick="dis()">답변</button> -->
								<script type="text/javascript">
							        function dis(r_num){
							            if($('#dis'+r_num).css('display') == 'none'){
							            $('#dis'+r_num).show();
								        }else{
								            $('#dis'+r_num).hide();
								        }
							        }
							    </script>
<!-- 					            <button>답변</button> -->
					            
					            <c:if test="${sessionID == reply.m_id || sessionID == 'aaaaaa@aaaaaa.com' }">
								<button type="button" onclick="location.href='${pageContext.request.contextPath}/reply/replyDelete?bt_num=${reply.bt_num }&bc_num=${reply.bc_num }&p_num=${reply.p_num}&r_num=${reply.r_num}'">삭제</button>
								</c:if>
					        </c:if>
					        <hr>
						</div>
						</c:forEach>
					</div>
					<div class="con_item">
						<div class="item_tit">참여/취소안내</div>
						<div class="item_det">
							<div class="can_detail">
								* 모임의 신청/취소/변경/환불은 참여신청 기간 내에만 가능합니다.<br /> * 결제한 유료모임은 환불 시 결제
								수단과 환불 시점에 따라 수수료가 부과될 수 있습니다. 자세한 사항은 <a>취소/환불약관</a>을 확인해주세요.<br />
								* 결제, 환불, 참여신청 수정/취소, 참여상태 확인, 참여내역 확인은 마이페이지에서 할 수 있습니다.<br />
								* 모임 또는 그룹의 설정, 모집정원 초과 여부에 따라 대기자로 선정될 수 있습니다. <a
									href="open.html">자세히보기</a><br /> * 온오프믹스 결제서비스를 이용하는 모임은 개설자의
								사업자 여부에 따라 결제증빙 발행이 가능합니다. <a>자세히보기</a><br /> * 개설자 선정방식 또는 개설자
								통장입금 방식의 모임 참여/결제 확인은 개설자에게 문의 바랍니다.<br /> * 온오프믹스는 참여신청 및 참가비
								결제 기능을 제공하는 회사로 모임개설자(주최측)가 아닙니다. 모임 내용과 관련한 사항은 모임 개설자에게 문의
								바랍니다.
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<%@ include file="/WEB-INF/views/main/footer.jsp"%></body>
</html>