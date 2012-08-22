package net.avantic.scaffolding.java.spring.mvc.template.view

class ListViewTemplate {

	def clazz

	def config

	def buildTableHeaders() {
		def output = ""
		clazz.declaredFields.findAll{!it.synthetic}.each { field ->
			output += """
			<th title=\"<spring:message code=\"${config.beanName}.list.${field.name}.title\"/>\">
				<a href=\"#\"><spring:message code=\"${config.beanName}.list.${field.name}\"/></a>
			</th>
			"""
		}

		output
	}

	def buildData() {
		def output = ""
		clazz.declaredFields.findAll{!it.synthetic}.each { field ->
			if (field.type.name == "org.joda.time.DateTime") {
				output += """
				<td class=\"date\">
					<joda:format value=\"\${${config.beanName}.${field.name}}\" pattern=\"dd/MM/yyyy\"/>
				</td>
				"""	
			} else {
				output += """
				<td>
					\${${config.beanName}.${field.name}}
				</td>
				"""			
			}
		}

		output
	}

	def build() {
"""
<!--
Autogenerated file, please check for the next items:

FIX_TEMPLATE_FILE
FIX_TEMPLATE_NAME
FIX_ACTIVE_TAB_SELECTOR
FIX_ACTIVE_FIELD_SELECTOR
FIX_TABLE_ID
-->

<%@ page contentType=\"text/html; charset=UTF-8\" %>

<%@ include file=\"/WEB-INF/views/includes/includes.jsp\" %>

<tiles:insertTemplate template=\"/WEB-INF/views/layouts/FIX_TEMPLATE_FILE.jsp\">

	<tiles:putAttribute name=\"FIX_TEMPLATE_NAME\" cascade=\"true\">
	
		<script type=\"text/javascript\" charset=\"utf-8\">
			deseleccionarElementosNavList();
			deseleccionarElementosMenu();
			\$(\"#FIX_ACTIVE_TAB_SELECTOR\").addClass(\"active\");
		
	        var \$table;
	        
	        \$(\"#FIX_ACTIVE_FIELD_SELECTOR\").addClass(\"active\");
	        
			\$(document).ready(function() {
				\$table = \$(\"#FIX_TABLE_ID\").dataTable({
			    	\"bPaginate\" : false,
			    	\"aaSorting\" : [[0, \"desc\"]],
			    	\"oLanguage\" : dataTableSpanishTags
			    });
				
				
			});
				
		</script>
	
		<div class=\"row-fluid\">
			<div class=\"span12\">
				

				<div class=\"well\">
					<fieldset>
						<legend>
							<spring:message code=\"${config.beanName}.list.title\"/>
						</legend>
					
						<div class=\"tabla\" style=\"margin-bottom: 2em;\">
							<table id=\"FIX_TABLE_ID\">
								<thead>
									<tr>
										${buildTableHeaders()}
									</tr>
								</thead>							
								<tbody>
									<c:forEach items=\"\${${config.beanName}s}\" var=\"${config.beanName}\" varStatus=\"status\">
									<tr class=\"\${(status.index % 2) == 0 ? 'odd' : 'even'}\">
										${buildData()}
									</tr>
									</c:forEach>	
								</tbody>
							</table>
						</div>
					</fieldset>
				</div>			
			</div><!--/span-->
		</div><!--/row-->
	</tiles:putAttribute>
</tiles:insertTemplate>
"""
	}

}