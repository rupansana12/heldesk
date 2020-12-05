package uem.co.mz.helpdesk.exel;

import java.util.ArrayList;
import java.util.List;
/*
import mz.ciuem.sgea.entity.CicloCurso;
import mz.ciuem.sgea.service.CicloCursoService;*/

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyViewModel {


	/*public List<CicloCurso> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<CicloCurso> ordersList) {
		this.ordersList = ordersList;
	}

	@Init
	public void init() {
		// crudService = (CRUDService) SpringUtil.getBean("CRUDService");
		ordersList = _cicloCursoService.getAll();
	}*/

	public String getCustomerName(String firstName, String lastName) {
		return firstName + " " + lastName;
	}

	@Command
	public void onExcelExport() {
		List<ExcelColumns> excelColumns = new ArrayList<ExcelColumns>();
		excelColumns
				.add(new ExcelColumns("cicloUniversidade", "Universidade", FormatType.INTEGER));
		excelColumns.add(new ExcelColumns("codigo", "Codigo", FormatType.TEXT));

		excelColumns
				.add(new ExcelColumns("tipo", " Operacao", FormatType.TEXT));
		excelColumns
				.add(new ExcelColumns("data", "Data", FormatType.DATE, 5000));
		excelColumns.add(new ExcelColumns("hora", "Hora", FormatType.DATE));
		/*
		 * excelColumns.add(new ExcelColumns("customerAddress1", "Address1",
		 * FormatType.TEXT,5000)); excelColumns.add(new
		 * ExcelColumns("customerCity", "City", FormatType.TEXT));
		 * excelColumns.add(new ExcelColumns("customerState", "State",
		 * FormatType.TEXT,2000)); excelColumns.add(new
		 * ExcelColumns("customerZip", "Zip", FormatType.TEXT));
		 * excelColumns.add(new ExcelColumns("customerPhone", "Phone",
		 * FormatType.TEXT)); excelColumns.add(new ExcelColumns("orderTotal",
		 * "Order Total", FormatType.MONEY));
		 */

		BeanToExcel beanToExcel = new BeanToExcel();
		beanToExcel.setExcelColumns(excelColumns);
		beanToExcel.setDataSheetName("cicloCurso");
		/*beanToExcel.setDataList(ordersList);*/
		beanToExcel.exportToExcel();
	}

}
