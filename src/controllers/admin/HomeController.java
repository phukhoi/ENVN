package controllers.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.Thematic;
import pojo.User;
import utils.CurrentContext;
import dao.Entities;
import dao.PermissionsDAO;

@Controller("AdminController")
@RequestMapping(value = "/admin/home")
public class HomeController extends controllers.Controller
{
	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest request)
	{
		Entities context = new Entities();
		User user = CurrentContext.getCurrentUser(request);
		List<Thematic> thematics = null;
		if (user != null)
		{
			int permission = user.getPermission().getPermissionId();
			if (user.getPermission().getPermissionId() == PermissionsDAO.PERMISSION_CONTENT_ADMIN)
			{
				thematics = context.getThematics().getThematics(user);
			}
			if (user.getPermission().getPermissionId() == PermissionsDAO.PERMISSION_WEB_ADMIN)
			{
				thematics = context.getThematics().toList();
			}
			
		}
		model.addAttribute("listThematic", thematics);
		return "admin/home/index";
	}
}
