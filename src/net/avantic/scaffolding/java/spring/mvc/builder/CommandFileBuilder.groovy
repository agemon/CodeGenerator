package net.avantic.scaffolding.java.spring.mvc.builder

import net.avantic.scaffolding.java.spring.mvc.template.TemplateFactory

class CommandFileBuilder extends AbstractFileBuilder {

	def clazz

	def config

	def build() {
		createFile(
			"${config.generationFolder}/${config.commandPath}", 
			"Create${config.className}Request.java", 
			writeCreateCommandCode)
	}

	def writeCreateCommandCode = {file -> 
		def template = TemplateFactory.getCreateCommandTemplate(clazz, config)
		file.write template.build()
	}

}