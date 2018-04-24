package cn.edu.gdaib.mapper;

import java.util.List;

import cn.edu.gdaib.domain.Visitor;

public interface VisitorMapper {
	List<Visitor> getVisitorList();
	Visitor getVisitorById(long id);
	void insertVisitor(Visitor visitor);
	void updateVisitor(Visitor visitor);
	void deleteVisitor(long id);
}
