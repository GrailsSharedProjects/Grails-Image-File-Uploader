package uploader
import grails.converters.JSON

class UploadController {

    def index() {
    	println "inside controller: " + params       
    	if(params.file){
    	    	def f = request.getFile('file');
			    	println f.size;
			    	if(!f.empty) {
			            def webRootDir = servletContext.getRealPath("/")  
			            println webRootDir  
			            def userDir = new File(webRootDir, "/upload/")  
			            userDir.mkdirs();
			            def localFile = new File( userDir, f.originalFilename);
			            f.transferTo(localFile)
			            def msg = new Msg(key:"success", value:localFile.name);
			            render msg as JSON
			            return;
			      }
			      render new Msg(key:"error", value:"file is empty.") as JSON
			      return;
    	}
    	render new Msg(key:"error", value:"no file uploaded.") as JSON
    }
    
    def success(){
    	def url = params.id + "." + params.format;
    	println url
    	render view:'success', model:['imageUrl': url]
    }
}
