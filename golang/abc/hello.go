package abc
import (
	"fmt"
	"os"
)
func GoHello(fd int32) int64{
	out:=os.NewFile(uintptr(fd), "")
	defer out.Close()
	str:=fmt.Sprintf("hello from golang, pid: %d\n", os.Getpid())
	len,_:=out.Write([]byte(str))
	return int64(len)
}