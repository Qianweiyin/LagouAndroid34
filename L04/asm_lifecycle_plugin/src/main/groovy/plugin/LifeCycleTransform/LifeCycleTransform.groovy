import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import groovy.io.FileType


/**
 * Package name mismatch. Actual: '', expected: 'plugin.LifeCycleTransform'
 *
 * Class LifeCycleTransform is unused
 */
public class LifeCycleTransform extends Transform {

    @Override
    String getName() {
        return "LifeCycleTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)

        //拿到所有的class文件
        Collection<TransformInput> transformInputs = transformInvocation.inputs
        transformInputs.each { TransformInput transformInput ->
            //directoryInputs代表着以源码方式参与项目编译的所有目录结构及其目录下的源码文件
            //比如我们手写的类以及R.class、BuildConfig.class以及MainActivity.class等
            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                File dir = directoryInput.file
                if (dir) {
                    dir.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) { File file ->
                        System.out.println("find class : " + file.name)
                    }
                }
            }
        }
    }
}