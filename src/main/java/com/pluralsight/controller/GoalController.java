package com.pluralsight.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.pluralsight.model.GoalReport;
import com.pluralsight.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pluralsight.model.Goal;

import java.util.List;

@Controller
// will save the goal to the session
@SessionAttributes("goal")
public class GoalController {

	@Autowired
	private GoalService goalService;

	@RequestMapping(value = "addGoal", method = RequestMethod.GET)
	public String addGoal(Model model, HttpSession session) {
//		Goal goal = new Goal();

		// since we save the goal to the session attributes, can retrieve
		Goal goal = (Goal)session.getAttribute("goal");

		// if it doesn't exist, create a new one at 10 minutes
		if(goal == null) {
			goal = new Goal();
			goal.setMinutes(10);
		}
		model.addAttribute("goal", goal);
		
		return "addGoal";
	}
	
	@RequestMapping(value = "addGoal", method = RequestMethod.POST)
	public String updateGoal(@Valid @ModelAttribute("goal") Goal goal, BindingResult result) {
		
		System.out.println("result has errors: " + result.hasErrors());
		
		System.out.println("Goal set: " + goal.getMinutes());
		
		if(result.hasErrors()) {
			return "addGoal";
		} else {
			// can now link up to the service to save the goal
			goalService.save(goal);
		}
		
		return "redirect:index.jsp";
	}

	@RequestMapping(value = "getGoals", method= RequestMethod.GET)
	public String getGoals(Model model) {

		List<Goal> goals = goalService.findAllGoals();

		model.addAttribute("goals", goals);

		return "getGoals";

	}

	@RequestMapping(value = "getGoalReports", method = RequestMethod.GET)
	public String getGoalReports(Model model) {
		List<GoalReport> goalReports = goalService.findAllGoalReports();

		model.addAttribute("goalReports", goalReports);

		return "getGoalReports";
	}
	
}