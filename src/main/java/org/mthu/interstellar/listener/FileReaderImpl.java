package org.mthu.interstellar.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mthu.interstellar.exception.LoopsNotAllowedAcception;
import org.mthu.interstellar.model.Planet;
import org.mthu.interstellar.model.Route;
import org.mthu.interstellar.repository.PlanetJpaRepository;
import org.mthu.interstellar.repository.RouteJpaRepository;
import org.mthu.interstellar.service.PlanetService;
import org.mthu.interstellar.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileReaderImpl implements FileReader{

		
	@Autowired
	RouteService routeService;
	
	@Autowired
	PlanetService planetService;
	
	@Value(value = "${xlsx.data.file.location}")
	private String XLSX_DATA_FILE;
	
	@Override
	public void ReadXslxFile() {
		try {
			Workbook workbook = createWorkBook();
			readPlanets(workbook);
			readRoutes(workbook);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}		
	}
	
	private Workbook createWorkBook() throws FileNotFoundException, IOException {
		InputStream fis = this.getClass().getResourceAsStream("/graph.xlsx");
		
		//FileInputStream xlsFile = new FileInputStream(new File(XLSX_DATA_FILE));
	
		return new XSSFWorkbook(fis);
	}
	
	private void readPlanets(Workbook workbook) throws FileNotFoundException, IOException {
		
		Sheet planetsSheet = getSheet(workbook, 0);
		
		Iterator<Row> iterator = planetsSheet.iterator();
		
		while (iterator.hasNext()) {
			
			Row currentRow = iterator.next();
			
			Cell planetIdCell = currentRow.getCell(0);
			
			Cell planetNameCell = currentRow.getCell(1);
			
			if ((planetIdCell.getCellType() == Cell.CELL_TYPE_STRING) && (planetNameCell.getCellType() == Cell.CELL_TYPE_STRING)) {
				
				//savePlanets(planetIdCell.getStringCellValue(), planetNameCell.getStringCellValue());
				Planet planet = new Planet();
				planet.setName(planetNameCell.getStringCellValue());
				planet.setNode(planetIdCell.getStringCellValue());
				planetService.save(planet);
			}
		}
	}
	
	private Sheet getSheet(Workbook workBook, int index) throws FileNotFoundException, IOException {
		return workBook.getSheetAt(index);
	}
	
	private void readRoutes(Workbook workbook) throws FileNotFoundException, IOException {
		
		Sheet routesSheet = getSheet(workbook, 1);
		
		Iterator<Row> it = routesSheet.iterator();
		
		while (it.hasNext()) {
			Row row = it.next();
			Cell idCell = row.getCell(0);
			Cell sourceCell = row.getCell(1);
			Cell destCell = row.getCell(2);
			Cell weightCell = row.getCell(3);
			int routeID = 0;
			String source = " ";
			String destination = " ";
			float distance = 0.0f;
			
			if (idCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				
				routeID = (int) idCell.getNumericCellValue();
				
			} else {
				
				continue;
			}
			if (sourceCell.getCellType() == Cell.CELL_TYPE_STRING) {
				
				source = sourceCell.getStringCellValue();
			}
			if (destCell.getCellType() == Cell.CELL_TYPE_STRING) {
				
				destination = destCell.getStringCellValue();
			}
			if (weightCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				
				distance = (float) weightCell.getNumericCellValue();
			}
			
			if(source != null && destination != null){
			Route route = new Route();
			route.setId(routeID);
			route.setOrigin(source);
			route.setDestination(destination);
			route.setDistance(distance);
			routeService.save(route);
			}
			
		}
	}
}
