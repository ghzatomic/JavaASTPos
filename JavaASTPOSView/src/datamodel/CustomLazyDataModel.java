package datamodel;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public abstract class CustomLazyDataModel<T> extends LazyDataModel<T>{

	private static final long serialVersionUID = 3596900977579370453L;

	@Override
	public void setRowIndex(final int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
	
	public List<T> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		Map<String, Object> filtros = new TreeMap<String, Object>();
		for (Map.Entry<String, Object> iterable_element : filters.entrySet()) {
			filtros.put(iterable_element.getKey(), iterable_element.getValue());
		}
		return loadData(first, pageSize, sortField, sortOrder,filtros);
		//return loadData(first, 0, sortField, sortOrder, filters);
	}
	
	public abstract  List<T> loadData(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
	
	/*public void forEach(Consumer<? super T> action) {
		// TODO Auto-generated method stub
		
	}
	
	 (non-Javadoc)
	 * @see java.lang.Iterable#spliterator()
	 
	public Spliterator<T> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
