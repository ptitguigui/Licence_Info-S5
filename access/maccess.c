#include <unistd.h>
#include <stdio.h>

int vflag, rflag, wflag, xflag;

void usage (char *prog) {
   printf("usage : %s [rvwx] file", prog);
}

int maccess (int argc, char **argv) {
   int i;
   printf("OPTIONS verbose:%i, read:%i, write:%i, exec:%i\n",vflag,rflag,wflag,xflag);
   printf("REMAINING %i ARGS:\n",argc);
   for (i = 0; i < argc; i++) {
      printf("\t%s\n",argv[i]);
   }
   return 1;
}

int main (int argc, char **argv) {
   int ch;
   vflag = 0;
   rflag = 0;
   wflag = 0;
   xflag = 0;
   while ((ch = getopt(argc, argv, "vrwxh")) != -1) {
      switch (ch) {
      case 'v':
         vflag = 1;
         break;
      case 'r':
         rflag = 1;
         break;
      case 'w':
         wflag = 1;
         break;
      case 'x':
         xflag = 1;
         break;
      case 'h':
      default:
         usage(argv[0]);
      }
   }
   argc -= optind;
   argv += optind;
   return maccess(argc,argv);
}
