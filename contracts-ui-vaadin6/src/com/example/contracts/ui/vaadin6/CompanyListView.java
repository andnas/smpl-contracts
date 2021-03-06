package com.example.contracts.ui.vaadin6;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;

import com.example.contracts.model.Company;
import com.example.contracts.model.DictOrgForm;
import com.example.contracts.services.CompanyService;
import com.example.contracts.utils.SpringUtil;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class CompanyListView extends CustomComponent {

    @AutoGenerated
    private VerticalLayout mainLayout;

    @AutoGenerated
    private Table companyListTable;

    @AutoGenerated
    private Panel panel_2;

    @AutoGenerated
    private GridLayout gridLayout_1;

    @AutoGenerated
    private Button applayButton;

    @AutoGenerated
    private ComboBox orgFormComboBox;

    @AutoGenerated
    private Label label_1;

    private static final long serialVersionUID = 1L;

    private ApplicationContext ac;

    private PageSizeConfig psConfig;

    private CompanyService service;

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /**
     * The constructor should first build the main layout, set the composition root and then do any custom initialization.
     *
     * The constructor will not be automatically regenerated by the visual editor.
     */
    public CompanyListView() {
	buildMainLayout();
	setCompositionRoot(mainLayout);

	// add user code here
	ac = SpringUtil.getApplicationContext();
	psConfig = ac.getBean(PageSizeConfig.class);

	// CompanyService service = (CompanyService) ac.getBean("companyService");
	// Так тоже работает, не смотря на то, что здесь запрашивается бин по классу интерфейса,
	// а в конфигурации указан класс имплементации
	service = ac.getBean(CompanyService.class);
	//service = (CompanyService) ac.getBean("companyServiceImpl");
	
	fillOrgFormsComboBox(service);

	companyListTable.addContainerProperty("Орг.форма", String.class, null);
	companyListTable.addContainerProperty("Краткое  наименование", String.class, null);
	companyListTable.addContainerProperty("Полное наименование", String.class, null);

	fillCompanies(service);
	
	applayButton.addListener(new Button.ClickListener() {
	    private static final long serialVersionUID = 1L;
	    public void buttonClick(ClickEvent event) {
	        fillCompanies(service);
	    }});

    }

    private void fillCompanies(CompanyService service) {
	Integer selectedOrgFormId = (Integer) orgFormComboBox.getValue();
	Collection<Company> companies = service.getCompanyList(selectedOrgFormId);
	int i = 0;
	companyListTable.removeAllItems();
	for (Iterator<Company> iterator = companies.iterator(); iterator.hasNext();) {
	    Company company = iterator.next();
	    companyListTable.addItem(new Object[] { company.getDictOrgForm().getName(), company.getShortName(), company.getName() }, i++);
	}
	
	// TODO разобраться почему не включается пейджинг
	companyListTable.setPageLength(psConfig.getCompanyList());
    }
    
    private void fillOrgFormsComboBox(CompanyService service) {
	Collection<DictOrgForm> orgForms = service.findAllOrgForms();
	if (orgForms.size() == 0) {
	    orgFormComboBox.setInputPrompt("(справочник не доступен)");
	}

	for (Iterator<DictOrgForm> iterator = orgForms.iterator(); iterator.hasNext();) {
	    DictOrgForm dictOrgForm = (DictOrgForm) iterator.next();
	    orgFormComboBox.addItem(dictOrgForm.getId());
	    orgFormComboBox.setItemCaption(dictOrgForm.getId(), dictOrgForm.getName());
	}

	// orgFormComboBox.setNullSelectionAllowed(false);
	orgFormComboBox.setImmediate(true);
    }

    public static CompanyListView getCompanyListView() {
	return new CompanyListView();
    }

    @AutoGenerated
    private VerticalLayout buildMainLayout() {
	// common part: create layout
	mainLayout = new VerticalLayout();
	mainLayout.setImmediate(false);
	mainLayout.setWidth("100%");
	mainLayout.setHeight("100%");
	mainLayout.setMargin(false);
	
	// top-level component properties
	setWidth("100.0%");
	setHeight("100.0%");
	
	// panel_2
	panel_2 = buildPanel_2();
	mainLayout.addComponent(panel_2);
	
	// companyListTable
	companyListTable = new Table();
	companyListTable.setImmediate(false);
	companyListTable.setWidth("100.0%");
	companyListTable.setHeight("100.0%");
	mainLayout.addComponent(companyListTable);
	mainLayout.setExpandRatio(companyListTable, 1.0f);
	
	return mainLayout;
    }

    @AutoGenerated
    private Panel buildPanel_2() {
	// common part: create layout
	panel_2 = new Panel();
	panel_2.setCaption("Список компаний");
	panel_2.setImmediate(false);
	panel_2.setWidth("100.0%");
	panel_2.setHeight("100px");
	
	// gridLayout_1
	gridLayout_1 = buildGridLayout_1();
	panel_2.setContent(gridLayout_1);
	
	return panel_2;
    }

    @AutoGenerated
    private GridLayout buildGridLayout_1() {
	// common part: create layout
	gridLayout_1 = new GridLayout();
	gridLayout_1.setImmediate(false);
	gridLayout_1.setWidth("100.0%");
	gridLayout_1.setHeight("100.0%");
	gridLayout_1.setMargin(false);
	gridLayout_1.setColumns(2);
	gridLayout_1.setRows(2);
	
	// label_1
	label_1 = new Label();
	label_1.setImmediate(false);
	label_1.setWidth("-1px");
	label_1.setHeight("-1px");
	label_1.setValue("Форма собственности:");
	gridLayout_1.addComponent(label_1, 0, 0);
	gridLayout_1.setComponentAlignment(label_1, new Alignment(34));
	
	// orgFormComboBox
	orgFormComboBox = new ComboBox();
	orgFormComboBox.setImmediate(false);
	orgFormComboBox.setWidth("-1px");
	orgFormComboBox.setHeight("-1px");
	gridLayout_1.addComponent(orgFormComboBox, 1, 0);
	gridLayout_1.setComponentAlignment(orgFormComboBox, new Alignment(33));
	
	// applayButton
	applayButton = new Button();
	applayButton.setCaption("Применить");
	applayButton.setImmediate(true);
	applayButton.setWidth("-1px");
	applayButton.setHeight("-1px");
	gridLayout_1.addComponent(applayButton, 1, 1);
	gridLayout_1.setComponentAlignment(applayButton, new Alignment(33));
	
	return gridLayout_1;
    }

}
