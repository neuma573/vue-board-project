package com.lguplus.medialog.project.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.medialog.project.base.auth.AuthService;
import com.lguplus.medialog.project.common.utils.DownloadUtils;
import com.lguplus.medialog.project.common.utils.SpringUtils;

import lombok.Data;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@Data
@ConfigurationProperties("app.brd")
@RequestMapping("/board")
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private boolean viewCntByCookie;
	private String fileDownload;

	@Autowired
	private BoardService svc;
	private File file;
	HttpSession session;
	BoardVO board;
	PageVO pageVO;
	String userId = SpringUtils.getCurrentUserName();
	private AuthService authSvc;
//	@GetMapping("")
//	public List boardList(PageVO pageVO, Model model, HttpSession session) throws Exception {
//		int pageNum = 33;
//		pageVO.setDisplayRowCount(pageNum);
//		pageVO.pageCalculate(svc.selectBoardCount(pageVO));
//		List<BoardVO> listview = svc.selectBoardList(pageVO);
//		JSONObject listObj = new JSONObject();
//		JSONArray list = new JSONArray();
//		String userId = "123";
//            
//        model.addAttribute("userId",userId);
//        logger.info(model.toString());
//		return listview;
//	}

	
	
    @GetMapping("")
    public Map<String, Object> boardList(@RequestParam(value = "page", required = false) Integer id, PageVO pageVO, HttpSession session) throws Exception{  	
    	if(id==null) {
    		id=1;
    	}logger.info("????????? ???????????????"+id.toString());
    	Map<String, Object> data = new HashMap();
    	String userId =SpringUtils.getCurrentUserName();
    	pageVO.setPage(id);
    	pageVO.setDisplayRowCount(10);
		pageVO.pageCalculate(svc.selectBoardCount(pageVO));
		List<BoardVO> listview = svc.selectBoardList(pageVO);
    	this.pageVO = pageVO;
    	data.put("boardList", listview);
    	data.put("userId", userId);
    	data.put("pageVO", pageVO);
    	logger.info(session.toString());
    	logger.info(pageVO.toString());
        return data;
    }

	/* ???????????? ????????? ????????? ??? ????????? ?????? */
	@GetMapping(value = "/setpagenum")
	 public Map<String, Object> setPageCnt(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpSession session, PageVO pageVO) throws Exception {
    	Map<String, Object> data = new HashMap();
		int pageNum = (int) requestJsonHashMap.get("pageNum");
		logger.info("???????????????"+pageNum);
		pageVO.setPage(pageNum);
		pageVO.pageCalculate(svc.selectBoardCount(pageVO));
		List<BoardVO> listview = svc.selectBoardList(pageVO);
		this.pageVO = pageVO;
    	data.put("boardList", listview);
    	data.put("userId", userId);
    	data.put("pageVO", pageVO);
    	logger.info(session.toString());
    	logger.info(pageVO.toString());
		
    	return data;
        
    }

//	/* ???????????? ????????? ????????? ??? ????????? ?????? */
//	@RequestMapping(value = "/setPageCnt", method = RequestMethod.POST)
//	public String setPageCnt(@RequestParam(value = "displayRowCount") Integer displayRowCount, HttpSession session) {
//		session.setAttribute("displayRowCount", displayRowCount);
//		logger.info("????????? ?????? ?????? ::" + displayRowCount);
//
//		return "redirect:/page/board";
//	}

	

//	/* ???????????? ????????? ????????? ??? ????????? ?????? */
//	@RequestMapping(value = "/setPageCnt")
//	public String setPageCnt(@RequestParam(value = "displayRowCount") Integer displayRowCount, HttpSession session) {
//		session.setAttribute("displayRowCount", displayRowCount);
//		logger.info("????????? ?????? ?????? ::" + displayRowCount);
//
//		return "redirect:/page/board";
//	}
//
//	/* ????????? ?????? ?????? */
//	@RequestMapping(value = "/setPagingMethod")
//	public String setPaging(HttpSession session) {
//		if (session.getAttribute("pagingMethod") == "new") {
//			session.setAttribute("pagingMethod", "re");
//		} else {
//			session.setAttribute("pagingMethod", "new");
//		}
//
//		return "redirect:/page/board";
//	}

	/* ????????? ????????? ?????? */
//	@GetMapping("")
//	public String boardList(PageVO pageVO, Model model, HttpSession session) throws Exception {
//		String String = "";
//		if (session.getAttribute("displayRowCount") == null) {
//			session.setAttribute("displayRowCount", "10");
//		}
//		if (session.getAttribute("pagingMethod") == null) {
//			session.setAttribute("pagingMethod", "re");
//		}
//
//		logger.info("????????????: " + board);
//		String temp = session.getAttribute("displayRowCount").toString();
//		int pageNum = Integer.parseInt(temp);
//
//		pageVO.setDisplayRowCount(pageNum);
//		pageVO.pageCalculate(svc.selectBoardCount(pageVO));
//		List<?> listview;
//		if (session.getAttribute("pagingMethod") == "new") {
//			listview = svc.selectBoardListByNew(pageVO);
//		} else {
//			listview = svc.selectBoardList(pageVO);
//		}
//		model.addAttribute("list", listview);
//		model.addAttribute("pageVO", pageVO);
//		model.addAttribute("User", SpringUtils.getCurrentUserName());
//		model.addAttribute("select", String);
//		logger.info("pageVO ?????? :: " + pageVO);
//		session.setAttribute("rowCalculate", pageVO.getTotRow()-pageVO.getRowStart());
//		session.setAttribute("currPage", pageVO.getPage());
//		return "board/board.empty";
//	}
//
	/* ????????? ????????? ????????? */
	@GetMapping("/boardview")
	public  Map<String, Object> openBoardDetailget(@RequestParam(value = "id") int id) throws Exception {
    	Map<String, Object> data = new HashMap();
		List<ReplyVO> list = svc.openCommentList(id);
		FileVO listview = svc.getFileList(id);
		BoardVO board = svc.getBoardDetail(id);
		svc.boardViewUpdate(id);
    	data.put("replyList", list);
    	data.put("boardVO", board);
    	data.put("file", listview);
		this.board = board;
		logger.info("BoardVO??????: " + board);
		return data;

	}
//
//	
//	private void viewCountUp(Integer id, HttpServletRequest request, HttpServletResponse response) {
//		Cookie oldCookie = null;
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("postView")) {
//					oldCookie = cookie;
//				}
//			}
//		}
//
//		if (oldCookie != null) {
//			if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
//				svc.boardViewUpdate(id);
//				oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
//				oldCookie.setPath("/");
//				oldCookie.setMaxAge(60 * 60 * 24);
//				response.addCookie(oldCookie);
//			}
//		} else {
//			svc.boardViewUpdate(id);
//			Cookie newCookie = new Cookie("postView", "[" + id + "]");
//			newCookie.setPath("/");
//			newCookie.setMaxAge(60 * 60 * 24);
//			response.addCookie(newCookie);
//		}
//	}
//
//	/* ???????????? ?????? */
//	@PostMapping("searchResult")
//	public String boardSearchList(@RequestParam(value = "type") String type,
//			@RequestParam(value = "searchKeyword") String searchKeyword, Model model) {
//		List<BoardVO> listview;
//		switch (type) {
//		case "title":
//			listview = svc.searchBoardListByTitle(searchKeyword);
//			logger.info("???????????? ??????, ????????? ::" + searchKeyword);
//			model.addAttribute("list", listview);
//			model.addAttribute("title", "???????????? ?????? ?????????:"+searchKeyword);
//			break;
//		case "content":
//			listview = svc.searchBoardListByContent(searchKeyword);
//			logger.info("???????????? ??????, ????????? ::" + searchKeyword);
//			model.addAttribute("list", listview);
//			model.addAttribute("title", "?????????????????? ?????? ?????????:"+searchKeyword);
//			break;
//		case "selectOrigin":
//			listview = svc.searchBoardListByOrigin();
//			logger.info("????????? ?????? ::");
//			model.addAttribute("list", listview);
//			model.addAttribute("title", "????????? ???????????? ??????");
//			break;
//		case "selectChild":
//			listview = svc.searchBoardListByChild();
//			logger.info("????????? ??????");
//			model.addAttribute("list", listview);
//			model.addAttribute("title", "?????? ???????????? ??????");
//			break;
//
//		}
//
//		return "board/result.empty";
//	}
//
//	/* ????????? ????????? ??? ?????? */
//	@RequestMapping(value = "/boardWrite")
//	public String page(@RequestParam(required = false) String action, @RequestParam(required = false) Integer id,
//			@RequestParam(required = false) Integer origin, Model model) {
//		if (action == null) {
//			model.addAttribute("title", "????????? ??????");
//			return "board/somePage.empty";
//		}
//
//		BoardVO board = svc.getBoardDetail(id);
//
//		switch (action) {
//		case "modify":
//			if (!SpringUtils.getCurrentUserName().equals(board.getBrdWriter())) {
//				model.addAttribute("err", "[?????? ????????? ?????? ????????? ??? ????????????]");
//				return "board/BoardFailure";
//			}
//			model.addAttribute("title", id + "??? ????????? ??????");
//			model.addAttribute("action", "modify");
//
//			FileVO file = svc.getFileList(id);
//			model.addAttribute("file", file);
//
//			break;
//
//		case "reply":
//			model.addAttribute("title", id + "??? ???????????? ?????? ??????");
//			model.addAttribute("action", "reply");
//
//			model.addAttribute("id", id);
//			model.addAttribute("origin", origin);
//			break;
//		}
//		model.addAttribute("board", board);
//
//		return "board/somePage.empty";
//	}
//
//	/* ????????? ????????? */
	@RequestMapping(value = "/boardpost", method = RequestMethod.POST)
	public Map<String, Object> uploadBoard(@RequestBody HashMap<String, Object> requestJsonHashMap, BoardVO board, FileVO fileVO,
			 @RequestParam(required = false) MultipartFile uploadFile,
			 @RequestParam String action,
			 @RequestParam(required = false) Integer brdNo,
			 @RequestParam(required = false) Integer brdDepth) throws Exception {
		logger.info(requestJsonHashMap.toString());
		Map<String, Object> data = new HashMap();
		String userId = (String) requestJsonHashMap.get("brdWriter");
		String gotToken = (String) requestJsonHashMap.get("token");
		String realToken = svc.checkLog(userId);
		logger.info("????????????: "+gotToken);
		logger.info("????????????: "+realToken);
		if(!gotToken.equals(realToken)) {
			logger.info("?????? ?????????");
			data.put("status", "tokenNotMatch");
			return data;
		}
		
		
    	board.setBrdWriter((String) requestJsonHashMap.get("brdWriter"));
    	board.setBrdTitle((String) requestJsonHashMap.get("brdTitle"));
    	board.setBrdContent((String) requestJsonHashMap.get("brdContent"));
		logger.info(board.toString());
		String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		switch (action) {
		case "write":
			svc.uploadBoard(board);
			break;
		case "reply":
			board.setBrdDepth(formatDate);
			svc.uploadBoard(board);
			break;
		case "modify":
			board.setBrdNo(brdNo);
			board.setBrdModDt(formatDate);
			svc.boardModifyRegist(board);
			break;
		}

		if (!uploadFile.isEmpty()) {
			logger.info("??????????????? ?????? : " + uploadFile);
			logger.info(uploadFile.getName());
			logger.info(Long.toString(uploadFile.getSize()));
			logger.info("????????? : " + uploadFile.getOriginalFilename());
			uploadFile(fileVO, uploadFile, board);
		}
		data.put("status", "success");
		return data;
	}

	/* ??????????????? */
	public void uploadFile(FileVO fileVO, MultipartFile uploadFile, BoardVO board) throws IOException {
		String originalFileExtension = uploadFile.getOriginalFilename()
				.substring(uploadFile.getOriginalFilename().lastIndexOf("."));
		String storedFileName = SpringUtils.getCurrentUserName() + System.currentTimeMillis() + originalFileExtension;
		String filePath = fileDownload;
		file = new File(filePath + storedFileName);
		DownloadUtils.upload(uploadFile, file);
		
    	StringBuilder sb = new StringBuilder();
		SecureRandom num = new SecureRandom();
        for(int i=0; i<10; i++) {
        	sb.append((char)num.nextInt(61)+65);
        }
		

		fileVO.setFileRandomNo(sb.toString());
		fileVO.setFileBrdNo(board.getBrdNo());
		fileVO.setFileRealName(uploadFile.getOriginalFilename());
		fileVO.setFileSize(uploadFile.getSize());
		fileVO.setFileName(storedFileName);
		svc.uploadFile(fileVO);
	}

	/* ???????????? */
	@PostMapping(value = "/fileDownload")
	public void fileDownload(@RequestBody HashMap<String, Object> requestJsonHashMap,  HttpServletRequest request,
		HttpServletResponse response) throws IOException {
		
		String fileRandomNo = (String) requestJsonHashMap.get("fileRandomNo");
		Integer brdNo = (Integer) requestJsonHashMap.get("fileBrdNo");
		logger.info("??????????????? ????????? ?????? : " + brdNo);

		String path = fileDownload;
		FileVO fileVO = svc.getFileList(brdNo);

		if (!fileVO.getFileRandomNo().equals(fileRandomNo)) {
			logger.info(fileRandomNo + " :?????? ?????? ??????");
			return;
		}
		logger.info("?????? ?????? ?????? : " + fileRandomNo);

		String filename = fileVO.getFileName();

		String realPath = path + filename;
		logger.info("?????? ?????? ?????? " + realPath);

		file = new File(realPath);

		/* ????????? ?????? */
		String outputFileName = new String(fileVO.getFileRealName().getBytes("KSC5601"), "8859_1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputFileName + "\"");

		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(realPath);
		int ncount = 0;
		byte[] bytes = new byte[512];
		try {

			while ((ncount = fis.read(bytes)) != -1) {
				os.write(bytes, 0, ncount);
			}

		} catch (FileNotFoundException ex) {
			logger.info("???????????? ??????::" + ex);
		} catch (IOException ex) {
			logger.info("???????????? ??????::" + ex);
		} finally {
			fis.close();
			os.close();
		}
	}
//
	/* ???????????? */
//	@RequestMapping(value = "fileDelete")
//	public String deleteFile(@RequestParam("id") String id, @RequestParam("bid") Integer bid, Model model)
//			throws IOException {
//		if (id.equals("")) {
//
//		} else {
//			svc.deleteFile(id);
//		}
//		BoardVO board = svc.getBoardDetail(bid);
//		board.setBrdNo(bid);
//
//		if (!SpringUtils.getCurrentUserName().equals(board.getBrdWriter())) {
//			model.addAttribute("err", "[?????? ????????? ?????? ????????? ??? ????????????]");
//			return "board/BoardFailure";
//		}
//		model.addAttribute("title", id + "??? ????????? ??????");
//		model.addAttribute("action", "modify");
//		model.addAttribute("board", board);
//		return "board/somePage.empty";
//	}
	
	/* ????????? ????????? */
	@RequestMapping(value = "/boarddelete")
	public Map<String, Object> boardDelete(@RequestBody HashMap<String, Object> requestJsonHashMap, @RequestParam("id") Integer id, Model model, PageVO pageVO) throws Exception {
		Map<String, Object> data = new HashMap();
		logger.info(requestJsonHashMap.toString());
		String userId = (String) requestJsonHashMap.get("userId");
		String gotToken = (String) requestJsonHashMap.get("token");
		String realToken = svc.checkLog(userId);
		logger.info("????????????: "+gotToken);
		logger.info("????????????: "+realToken);
		if(!gotToken.equals(realToken)) {
			logger.info("?????? ?????????");
			data.put("status", "tokenNotMatch");
			return data;
		}
		
		if(!board.getBrdWriter().equals(userId)) {
			data.put("status", "noAuthDelete");
			return data;
		}
		else if (!svc.boardDelete(id)) {
			data.put("status", "childExsist");
			return data;
		}
		svc.deleteFileByParents(id);
		svc.commentDeleteByParents(id);
//		Integer currPage = Integer.parseInt(session.getAttribute("currPage").toString());
//		Integer rowCalculate = Integer.parseInt(session.getAttribute("rowCalculate").toString());
//		if (rowCalculate==0) {
//			currPage--;
//		}
//		logger.info(currPage+" ???????????? ???????????????");
//		logger.info("???????????? "+rowCalculate);
		
		data.put("status", "success");
		return data;
	}

//	/* ???????????? */
//	@RequestMapping(value = "/commentPost")
//	public String boardReplySave(ReplyVO boardReplyInfo) {
//		boardReplyInfo.setReWriter(SpringUtils.getCurrentUser().getUserId());
//		svc.insertBoardReply(boardReplyInfo);
//		svc.addCommentCnt(boardReplyInfo.getReBrdNo());
//
//		return "redirect:/page/board/boardView?id=" + boardReplyInfo.getReBrdNo();
//	}
//
//	/* ????????? */
//	@RequestMapping(value = "/commentDelete")
//	public String boardReplyDelete(@RequestParam("id") String id, @RequestParam("bid") String bid,
//			@RequestParam("reUser") String reUser, ReplyVO boardReplyInfo, Model model) {
//		String username = SpringUtils.getCurrentUserName();
//		logger.info(reUser + ":::::" + username);
//		if (!username.equals(reUser)) {
//			model.addAttribute("err", "[?????? ????????? ????????? ????????? ??? ????????????]");
//			return "board/BoardFailure";
//		} else if (!svc.deleteBoardReply(id)) {
//			model.addAttribute("err", "???????????? ???????????? ????????? ??? ????????????");
//			return "board/BoardFailure";
//		}
//		svc.subCommentCnt(bid);
//		return "redirect:/page/board/boardView?id=" + bid;
//	}
//
//	/* ??????????????? ?????? */
//	@RequestMapping(value = "/commentReply")
//	public String commentReply(@RequestParam("id") int id, @RequestParam("bid") Integer bid, Model model)
//			throws Exception {
//		model.addAttribute("id", id);
//		model.addAttribute("bid", bid);
//		return "board/commentReply.empty";
//	}

}