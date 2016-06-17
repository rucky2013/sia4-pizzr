package cc.picc.web;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.picc.entity.Article;
import cc.picc.repository.ArticleRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {

	private ArticleRepository articleRepo;

	@Autowired
	public ArticleController(ArticleRepository articleRepo) {
		this.articleRepo = articleRepo;
	}

	@RequestMapping(method = { GET })
	public String queryAll(Model model) {
		Pageable pageable = new PageRequest(0, 20,
				new Sort(new Order("author")));
		Page<Article> list = articleRepo.findAll(pageable);
		model.addAttribute("articles", list.getContent());
		return "article/index";
	}

	@RequestMapping(value="/{author}", method = GET)
	public String query(@PathVariable("author") String author, Model model) {
		List<Article> list = articleRepo.findByAuthor(author);
		model.addAttribute("articles", list);
		return "article/index";
	}

	@RequestMapping(method = POST)
	@Transactional(value = REQUIRED)
	public String submit(@Valid Article article, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "article/create";
		}
		article.setId(UUID.randomUUID().toString());
		articleRepo.save(article);
		return "redirect:article";
	}

	@RequestMapping(value = "/form", method = GET)
	public String form(Article article) {
		return "article/create";
	}

}
