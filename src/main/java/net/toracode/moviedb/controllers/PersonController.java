package net.toracode.moviedb.controllers;

import net.toracode.moviedb.Commons.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import net.toracode.moviedb.entities.Person;
import net.toracode.moviedb.services.PersonService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private ImageValidator imageValidator;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String personListPaginated(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "size", required = false) Integer size, Model model) {

        // if params aren't present return all items
        if (page == null || size == null) {
            page = 0;
            size = 10;
        }
        List<Person> personList = this.personService.getAllPersonsPaginated(page, size);
        model.addAttribute("personList", personList);
        model.addAttribute("page", page);
        return "person/all";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String personDetails(@PathVariable("id") Long id, Model model) {
        Person person = this.personService.getPersonById(id);
        model.addAttribute("person", person);
        return "person/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPersonPage() {
        return "person/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPerson(@ModelAttribute("person") Person person, BindingResult bindingResult,
                               @RequestParam("image") MultipartFile multipartFile,
                               Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
        }
        if (multipartFile != null)
            if (imageValidator.isImageValid(multipartFile))
                person.setImage(multipartFile.getBytes());
        personService.save(person);
        model.addAttribute("message", "Successfully saved " + person.getName());
        return "person/create";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updatePersonPage(@PathVariable("id") Long id, Model model) {
        Person person = this.personService.getPersonById(id);
        model.addAttribute("person", person);
        return "person/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updatePerson(@ModelAttribute Person person, BindingResult bindingResult,
                               @PathVariable("id") Long id,
                               @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (bindingResult.hasErrors())
            System.out.println(bindingResult.toString());
        if (multipartFile != null)
            if (this.imageValidator.isImageValid(multipartFile))
                person.setImage(multipartFile.getBytes());
        person.setUniqueId(id);
        person = this.personService.save(person);
        return "redirect:/admin/person?message=" + person.getName() + " updated!";
    }

}
