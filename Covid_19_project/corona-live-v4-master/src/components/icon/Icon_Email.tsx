import React from "react";

import Icon from "./Icon";

import type { IconProps } from "@_types/icon-type";
import { theme } from "@styles/stitches.config";

const EmailIcon: React.FC<IconProps> = React.memo((props) => (
  <Icon {...props} type="fill">
    <svg
      viewBox="0 0 24 24"
      xmlns="http://www.w3.org/2000/svg"
      fill={theme.colors.gray800.computedValue}
    >
      <path
        fillRule="evenodd"
        d="M19,4 C20.6568542,4 22,5.34314575 22,7 L22,17 C22,18.6568542 20.6568542,20 19,20 L5,20 C3.34314575,20 2,18.6568542 2,17 L2,7 C2,5.34314575 3.34314575,4 5,4 L19,4 Z M20,7.328 L12.6585046,13.7525767 C12.3128975,14.054983 11.8110564,14.0801835 11.4394103,13.8281783 L11.3414954,13.7525767 L4,7.329 L4,17 C4,17.5522847 4.44771525,18 5,18 L19,18 C19.5522847,18 20,17.5522847 20,17 L20,7.328 Z M18.48,6 L5.518,6 L12,11.6712318 L18.48,6 Z"
      />
    </svg>
  </Icon>
));

export default EmailIcon;
